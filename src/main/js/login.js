async function submitForm() {
    /*
    Build the object we will transfer in our API call. Grab the values from
    the input elements above.
    */
    let authDto = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    }

    //Make our asynchronous API call
    let response = await loginRequest(authDto);

    console.log("Response: ", response);//fact finding
    console.log("Response JSON: ", response.json());//we should delete these later
    localStorage.setItem("isLoggedIn", false);

    if (response.status == 200) {//status 200 - OK - indicates successful login
        /*
        Here we are retyrieving the authToken header, which contains our username as
        passed back from the server. This way we can use it to identify ourselves in 
        subsequent API calls.
        For this to work, you must send back a token in the header of the response
        The key should match the shown key name "authToken" and the value should be
        a string you can use to locally store information about the logged in user.
        In this case we are getting a token that contains the username string, 
        and storing it in localStorage.
        */
        let token = response.headers.get("authToken");
        console.log("authToken received: ", token);//fact finding
        
        //navigate the window to the landing page
        window.location.href = "../index.html";
    } 
    else if (response.status == 308) {
        alert("Username does not exist!");

    }
    else if (response.status == 309) {
        alert("Incorrect password!");
    }
    else {
        alert("Unable to log in! Check username and password!");
    }
}