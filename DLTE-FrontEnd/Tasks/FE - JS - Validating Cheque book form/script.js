document.addEventListener('DOMContentLoaded', function() {
    var form = document.getElementById('myBankForm');
    form.addEventListener('submit', function(event) {
        event.preventDefault(); 


        var accountNumber = document.getElementById('accountNumber').value;
        var accountHolder = document.getElementById('accountHolder').value;
        var accountType = document.querySelector('input[name="accountType"]:checked');
        var chequeBook = document.getElementById('chequeBook').value;
        var dateOfApply = document.getElementById('dateOfApply').value;
        var termsConditions = document.getElementById('termsConditions').checked;
        var address = document.getElementById('address').value;
        var signature = document.getElementById('signature').value;
        var contactNumber = document.getElementById('contactNumber').value;
        var email = document.getElementById('email').value;

        // Clear previous error messages
        clearErrors();

      // Initialize isValid flag
var isValid = true;

// Check if account number is not empty and has 12 digits
if (accountNumber === '') {
    setError('accountNumberError', 'Account number is required.');
    isValid = false;
} else if (!/^\d{12}$/.test(accountNumber)) {
    setError('accountNumberError', 'Account number must be 12 digits.');
    isValid = false;
}

// Check if account holder name is not empty and matches the regex pattern
if (accountHolder === '') {
    setError('accountHolderError', 'Account holder name is required.');
    isValid = false;
} else if (!/^[a-zA-Z\s]*$/.test(accountHolder)) {
    setError('accountHolderError', 'Account holder name must contain only letters and spaces.');
    isValid = false;
}

// Check if account type is selected
if (!accountType) {
    setError('accountTypeError', 'Please select an account type.');
    isValid = false;
}

// Check if cheque book type is selected
if (chequeBook === '') {
    setError('chequeBookError', 'Please select a cheque book type.');
    isValid = false;
}

// Check if date of apply is selected
if (dateOfApply === '') {
    setError('dateOfApplyError', 'Date of apply is required.');
    isValid = false;
}

// Check if terms and conditions checkbox is checked
if (!termsConditions) {
    setError('termsConditionsError', 'You must agree to the terms and conditions.');
    isValid = false;
}

// Check if address is not empty
if (address === '') {
    setError('addressError', 'Address is required.');
    isValid = false;
}

// Check if signature file is selected
if (signature === '') {
    setError('signatureError', 'Please upload your signature.');
    isValid = false;
}

// Check if contact number is not empty, has 10 digits, and contains only digits
if (contactNumber === '') {
    setError('contactNumberError', 'Contact number is required.');
    isValid = false;
} else if (!/^\d{10}$/.test(contactNumber)) {
    setError('contactNumberError', 'Contact number must be 10 digits and contain only digits.');
    isValid = false;
}

// Check if email is not empty and valid
if (email === '') {
    setError('emailError', 'Email is required.');
    isValid = false;
} else {
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        setError('emailError', 'Please enter a valid email address.');
        isValid = false;
    }
}

// If all fields are valid, submit the form
if (isValid) {
    form.submit();
}

    function setError(id, message) {
        var errorElement = document.getElementById(id);
        errorElement.innerText = message;
    }

    function clearErrors() {
        var errorElements = document.querySelectorAll('.error');
        errorElements.forEach(function(element) {
            element.innerText = '';
        });
    }
})});
