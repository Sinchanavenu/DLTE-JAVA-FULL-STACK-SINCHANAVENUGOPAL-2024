const itemsPerPage = 2;
let currentPage = 1;

function fetchAccountDetails() {
    // Retrieve customer ID 
    let customerId = parseInt(document.getElementById('customerId').value);

    // Sample account details
    let customerAccounts = [
        {
            accountId: 101,
            accountNumber: 111111111110,
            customerId: 1,
            accountType: "Savings",
            accountStatus: "Active",
            accountBalance: 5000.00
        },
        {
            accountId: 102,
            accountNumber: 111111111111,
            customerId: 1,
            accountType: "Salary",
            accountStatus: "Active",
            accountBalance: 3000.00
        },
        {
            accountId: 103,
            accountNumber: 111111111112,
            customerId: 1,
            accountType: "Current",
            accountStatus: "Active",
            accountBalance: 53000.00
        },
        {
            accountId: 104,
            accountNumber: 222222222220,
            customerId: 2,
            accountType: "Salary",
            accountStatus: "Active",
            accountBalance: 4000.00
        },
        {
            accountId: 105,
            accountNumber: 222222222221,
            customerId: 2,
            accountType: "Salary",
            accountStatus: "Inactive",
            accountBalance: 98000.00
        },
        {
            accountId: 106,
            accountNumber: 222222222222,
            customerId: 2,
            accountType: "Salary",
            accountStatus: "Active",
            accountBalance: 78000.00
        },
    ];

    // Filter customer accounts based on the provided customer ID and active status
    let activeAccounts = customerAccounts.filter(account => account.customerId === customerId && account.accountStatus === 'Active');
    

    displayAccountDetails(activeAccounts);
}

function displayAccountDetails(activeAccounts) {
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const paginatedAccounts = activeAccounts.slice(startIndex, endIndex);

    let accountDetailsContainer = document.getElementById('accountDetailsContainer');
    accountDetailsContainer.innerHTML = '';

    if (paginatedAccounts.length > 0) {
        paginatedAccounts.forEach(account => {
            let accountCard = document.createElement('div');
            accountCard.className = 'col-lg-6 account-box';
            accountCard.innerHTML = `
                <h3 class="account-details-title">Account Details</h3>
                <p><strong>Account ID:</strong> ${account.accountId}</p>
                <p><strong>Account Number:</strong> ${account.accountNumber}</p>
                <p><strong>Customer ID:</strong> ${account.customerId}</p>
                <p><strong>Account Type:</strong> ${account.accountType}</p>
                <p><strong>Account Status:</strong> ${account.accountStatus}</p>
                <p><strong>Account Balance:</strong> ${account.accountBalance}</p>
            `;
            accountDetailsContainer.appendChild(accountCard);
        });

        renderPagination(activeAccounts.length);
    } else {
        alert('No active accounts found for given id');
    }
}

function renderPagination(totalItems) {
    const totalPages = Math.ceil(totalItems / itemsPerPage);
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = '';

    if (totalPages > 1) {
        for (let i = 1; i <= totalPages; i++) {
            const liClass = (i === currentPage) ? 'page-item active' : 'page-item';
            const paginationHtml = `
                <li class="${liClass}">
                    <button class="page-link" onclick="changePage(${i})">${i}</button>
                </li>
            `;
            paginationContainer.innerHTML += paginationHtml;
        }
    }
}

function changePage(page) {
    currentPage = page;
    fetchAccountDetails();
}