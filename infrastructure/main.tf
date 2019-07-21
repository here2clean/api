provider "aws" {
  region = "eu-west-3"
}

resource "aws_key_pair" "aws-webserv-keypair"{
    key_name = "aws-webserv"
    public_key = "${file("/root/.aws/aws-web-serv.pub")}"
}


resource "aws_security_group" "aws-secgrp-allow-ssh" {
  name = "allow-ssh"
  
  ingress{
      from_port =22 
      to_port = 22
      protocol ="tcp" 
      cidr_blocks =["0.0.0.0/0"]

  }
  # Allow all outbound traffic.
  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "aws-secgrp-mysql-allow-querry" {
  name = "allo-mysql"

  ingress {
    from_port = 3306
    to_port = 3306
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
resource "aws_security_group" "aws-secgrp-allow-8085" {
  name = "allow-8085"

  ingress {
    from_port = 8085
    to_port = 8085
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_elb" "aws-loadbalancer" {

    name = "aws-loadbalancer"

    availability_zones = ["${aws_instance.aws-webserv.*.availability_zone}"]

    listener{
        instance_port = 8085
        instance_protocol = "http"
        lb_port=80
        lb_protocol = "http"
    }

      health_check {
        healthy_threshold   = 2
        unhealthy_threshold = 10
        timeout             = 18
        target              = "HTTP:8085/health"
        interval            = 20
        

    }

    instances = ["${aws_instance.aws-webserv.*.id}"]
  
}


resource "aws_instance" "aws-webserv" {
    ami = "ami-03bca18cb3dc173c9"
    instance_type = "t2.micro"
    key_name = "${aws_key_pair.aws-webserv-keypair.key_name}"
    security_groups = ["${aws_security_group.aws-secgrp-allow-ssh.name}","${aws_security_group.aws-secgrp-allow-8085.name}"]
    count = 2

}


resource "aws_db_instance" "aws-rds-mysql-instance" {
  allocated_storage    = 5
  storage_type         = "gp2"
  engine               = "mysql"
  engine_version       = "5.7"
  instance_class       = "db.t2.micro"
  name                 = "heretoclean"
  username             = "root"
  password             = "${file("/home/dbpwd")}"
  parameter_group_name = "default.mysql5.7"
  port                 =  3306
  skip_final_snapshot  = true
  publicly_accessible  = true
  vpc_security_group_ids = ["${aws_security_group.aws-secgrp-allow-ssh.id}","${aws_security_group.aws-secgrp-mysql-allow-querry.id}"]

    provisioner "local-exec" {
    command = "mysql --user=${aws_db_instance.aws-rds-mysql-instance.username} --password=${aws_db_instance.aws-rds-mysql-instance.password} --host=${aws_db_instance.aws-rds-mysql-instance.address} heretoclean < heretoclean.sql"
  }
}



output "output-mysql-address" {
  value = "${aws_db_instance.aws-rds-mysql-instance.address}"
}

data "aws_route53_zone" "cambar" {
  name = "cambar.re."
}

resource "aws_route53_record" "heretoclean" {
  zone_id ="${data.aws_route53_zone.cambar.id}"
  name = "heretoclean"
  type = "A"

  alias{
    name = "${aws_elb.aws-loadbalancer.dns_name}"
    zone_id = "${aws_elb.aws-loadbalancer.zone_id}"
    evaluate_target_health = true
  }
}



