Assumptions:

1. Month is always in English.
2. Input contains "payment month", instead of "payment start date". The input header (payment start date) is inconsistent with the
   input data (01 March - 31 March) in the exercise description. I believe that it makes sense that the input data is a given
   month of the year, and the program is supposed to output the start and end dates for that payment month.
3. Input file always contains a header.
4. Super rate is always a %.
5. Input file is in a text file format, with comma separated values.

Example input file is in test/resources/employee.txt. Contents as follows:

firstName,lastName,annualSalary,superannuationRate,paymentMonth
David,Rudd,60050,9%,March
Ryan,Chen,120000,10%,March

Instructions to build:

$ mvn clean compile assembly:single

Instructions to run:

$ java -jar payroll-manager-1.0-jar-with-dependencies.jar [csvfile]