function fetchAccountDetails() {
    // Retrieve customerId
    let customerId = parseInt(document.getElementById('customerId').value);

    // sample values
    let customerAccounts = [
        {
            accountId: 101,
            customerId: 1,
            accountType: "Savings",
            accountNumber: 111111111111,
            accountBalance: 5000.00,
            accountStatus: "Active"
        },
        {
            accountId: 102,
            customerId: 2,
            accountType: "Salary",
            accountNumber: 222222222220,
            accountBalance: 2000.00,
            accountStatus: "Active"
        },
        {
            accountId: 103,
            customerId: 2,
            accountType: "Salary",
            accountNumber: 222222222221,
            accountBalance: 87000.00,
            accountStatus: "Inactive"
        },
        {
            accountId: 104,
            customerId: 3,
            accountType: "Salary",
            accountNumber: 333333333330,
            accountBalance: 98600.00,
            accountStatus: "Active"

        },
    ];

    let activeAccounts = customerAccounts.filter(account => account.customerId === customerId && account.accountStatus === 'Active');


    let accountDetailsContainer = document.getElementById('accountDetailsContainer');
    accountDetailsContainer.innerHTML = '';

    if (activeAccounts.length > 0) {
        activeAccounts.forEach(account => {
            let accountCard = document.createElement('div');
            accountCard.className = 'account-box';
            accountCard.innerHTML = `
                <h3 class="account-details-title">Account Details</h3>
                <p><strong>Account ID:</strong> ${account.accountId}</p>
                <p><strong>Customer ID:</strong> ${account.customerId}</p>
                <p><strong>Account Type:</strong> ${account.accountType}</p>
                <p><strong>Account Number:</strong> ${account.accountNumber}</p>
                <p><strong>Account Balance:</strong> ${account.accountBalance}</p>
                <p><strong>Account Status:</strong> ${account.accountStatus}</p>
            `;

            accountCard.addEventListener('click', function() {
                showDetailedView(account);
            });
            accountDetailsContainer.appendChild(accountCard);
        });
    } else {
        alert('No active accounts found for the provided customer ID.');
    }
}