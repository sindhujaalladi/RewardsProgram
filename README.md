# RewardsProgram

In this RewardsProgram API I have implemented all the  scenarios  that are asked in the assessment.
Below are scenarios for the implementations.
I have attached document for outputs of each scneario(endpoint)

a) Calculates reward points for any given transcation Amount

It calculates rewards points for any given transcationAmount 
URL :http://localhost:9696/v1/rewards/transcationAmount
Method:GET 
Method Name : calculateRewardPointsPerTranscation()

b)create customerData and post to db 
It creates customerData and save the data into Database.Here i have used Mysql Database
Method:POST
Method Name : createCustomerData()

c) calculates reward points for the fisrt month for any given customer Id.
For any given customerId it calculates rewards points for the first month
Method : GET
Method Name : calculateRewardPointsPerMonth()

d) calculate total reward points earned per customer (for a 3 month period)
 It calculates total reward points earned by customer for total of 3 months
 Method : GET
 Method Name :calculateTotalRewardPointsPerCustomer()


e) calculate reward points per customer on a monthly basis 
It calculates reward points per customer on monthly basis and dispaly info for each month
Method : GET
Method Name : calculateRewardPointsPerEachMonth()


Junit test cases are written for positive and negative scenarion and all are passed




