Given a client makes a deposit of 1000 on 10/01/2012
And a deposit of 2000 on 13/01/2012
And a withdrawal of 500 on 14/01/2012
When she prints her bank statement
Then she would see
operation  |       date |  amount | balance
Withdrawal | 14/01/2012 |     500 | 2500
Deposit    | 13/01/2012 |    2000 | 3000
Deposit    | 10/01/2012 |    1000 | 1000