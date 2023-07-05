<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/public/side-nav-style.css">
</head>
<body>
<div>
    <nav class="topnav">
        <ul>
            <li><a href="sss">Progress</a></li>
            <li><a href="ddd">Profiles</a></li>
            <li><a href="ddd">Settings</a></li>
        </ul>
    </nav>
    <div class="attack-demo-window">
        <nav class="sidebar">
            <ul class="attack-menu">
                <li><a href="/public/brokenAuthentication.html">Broken Authentication</a></li>
                <li><a href="/public/sqlInjectionUnion.html">SQL injection Union</a></li>
                <li><a href="/public/commandInjection.html">Command Injection</a></li>
                <li><a href="/public/pathTraversal.html"> Path Traversal</a></li>
                <li><a href="/xsrf/online-banking">CSRF/XSRF</a></li>
                <li><a href="/public/xssStored.html">XSS - Stored</a></li>
                <li><a href="/xss/reflected/server-side/search-posts"> XSS - Reflected</a></li>
                <li><a href="/public/xxeInjection.html">XXE Injection</a></li>
            </ul>
        </nav>
        <main class="web-attacks-container">
            <div>
                Transfer Money
                <form method="post" action="/xsrf/online-banking/wire-transfer">
                    <div>
                        <label for="accounts">From Account:</label>
                        <select name="accountType" id="accounts">
                            <option value="CHECKING_ACCOUNT">Current Account</option>
                            <option value="CERTIFICATE_OF_DEPOSIT_ACCOUNT">CCertificate of deposit account</option>
                        </select>
                    </div>

                    <div>
                        <label for="receiver">Transfer To</label>
                        <input type="text" id="receiver" name="receiver">
                    </div>

                    <div>
                        <label for="receiver-IBAN">IBAN/Currency</label>
                        <input type="text" id="receiver-IBAN" name="receiverIBAN">
                        <select name="receiverAccountCurrency" id="receiver-account-currency">
                            <option value="BGN">BGN</option>
                            <option value="EUR">EUR</option>
                            <option value="USD">USD</option>
                        </select>
                    </div>
                    <div>
                        <label for="amount"> Amount</label>
                        <input type="number" id="amount" name="amount" step=".01">
                        <select name="amountCurrency" id="amount-currency">
                            <option value="BGN">BGN</option>
                            <option value="EUR">EUR</option>
                            <option value="USD">USD</option>
                        </select>
                    </div>
                    <div>
                        <label for="transfer-description">Description</label>
                        <input type="text" name="description" id="transfer-description">
                    </div>
                    <button type="submit" >Transfer</button>
                </form>
            </div>
        </main>
    </div>
</div>

</body>
</html>