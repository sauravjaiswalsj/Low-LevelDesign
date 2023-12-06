# Low Level Design

[![Peerlist](https://github-readme-badge.peerlist.io/api/sauravjaiswalsj)](https://peerlist.io/sauravjaiswalsj)

## ATM Design

### Design an Automated Teller Machine (ATM) System
Requirements:
1. User Management:
Users should be able to create accounts with a unique account number and a Personal Identification Number (PIN).
Each account should have a balance.
2. Authentication:
Users should be able to insert their ATM card and enter their PIN to access their account.
3. Transactions:
The ATM should support multiple transactions, including withdrawals, deposits, and balance inquiries.
4. Withdrawals should be possible in multiples of predefined denominations (e.g., $20, $50).
5. Balance Inquiry:
Users should be able to check their account balance at any time.
6. Security:
Implement security measures to prevent unauthorized access or PIN guessing.
Transactions should be secure and logged.
7. Transaction Logging:
Implement a transaction log that records details of each transaction.

# Features:

1. Card Insertion:
Users should be able to insert their ATM card into the machine.
2. PIN Entry:
Provide a secure PIN entry system.
3. Transaction Selection:
Users should be able to select the type of transaction (withdrawal, deposit, balance inquiry).
4. Withdrawal:
Allow users to withdraw money in predefined denominations.
5. Deposit:
Users should be able to deposit money into their accounts.
6. Balance Inquiry:
Provide an option for users to check their account balance.
7. Security Measures:
Implement measures to prevent unauthorized access and protect user information.
8. Transaction Logging:
Log each transaction with details such as time, type, amount, and account number.

### Solution
1. Adhered to clean coding
2. Used Dependency injection
3. Followed modularity and code Reusability
4. Enabled logging
