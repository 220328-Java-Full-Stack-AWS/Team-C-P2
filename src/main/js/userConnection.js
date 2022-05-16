//root URL for user
let userResourceURL = "http://localhost:8080/user";
let authResourceURL = "http://localhost:8080/auth";

async function loginRequest(authDto) {
    let response = await fetch (
        (authResourceURL + "/login"),
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                'Accept': 'application/json'
            },
            body: JSON.stringify(authDto)
        }
    );
    return response;
}