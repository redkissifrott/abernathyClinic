abernathyClinic

Medical management application who warns of the risk to have diabete

It consists of 3 Microservices :
- abernathyPatient
- abernathyNote
- abernathyReport
+ 1 client (UI) : abenathyClient


abernathyPatient works whith a MySQL database : abernathy_patient

SQL scripts for database schema and test data are in abernathyPatient folder (mySQLschema.sql and patients.sql)


abernathyNote works whith a MongoDB database : abernathy

curl script for test data is in abernathyNote folder (testNotes.txt)


To deploy the application in network of Dockers, launch docker-compose.yml that is in abernathyClinic folder
