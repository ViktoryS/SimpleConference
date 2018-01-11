<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
    <title>Register</title>
</head>
<body>
    <div class="header">
        <img src="../../images/prestige-logo.png">
        <div class="userMenu">
            <form method="post" action="">
                <input name="command" value="Home" type="hidden"/>
                <button type="submit">Home</button>
            </form>
            <form method="post" action="">
                <input name="command" value="Register" type="hidden"/>
                <button type="submit">Add event</button>
            </form>
            <form method="post" action="">
                <input name="command" value="ListBouquets" type="hidden"/>
                <button type="submit">Events</button>
            </form>
            <form method="post" action="">
                <input name="command" value="AddBouquet" type="hidden"/>
                <button type="submit">Lectures</button>
            </form>
        </div>
    </div>

    <div class="pagetitle">
        <h1>Register form</h1>
    </div>
    <div class="homecontent">
        <p class="success"></p>
        <form method="get">
        <table class="inputTable">
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Login:</td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="password" name="passwordRepeat"></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit">Register</button></td>
            </tr>
        </table>
        </form>
    </div>

</body>
</html>