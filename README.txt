Team Members:
Hua Cao, Jie Min.

The final report is in report.pdf

And the sql file is under ./sql/

To Launch in Local:

1. first install Docker

see https://docs.docker.com/get-docker/ for details.

2. run command: 
docker pull stangithubdocker/csc540-hcao5:latest
docker run --rm -p 8080:8080 --name test stangithubdocker/csc540-hcao5:latest

3. and then you can visit http://localhost:8080 to access the service.

Or you can use a published service:
http://152.7.98.242:8080/

the login info is:

admin/a for admin role
employee/e for employee role
student/s for student role

if you want to access the db:
the info is:

jdbcurl: jdbc:mysql://152.46.19.57:23333/upsdb?serverTimezone=America/New_York
username: root
password: csc540

database name: upsdb

