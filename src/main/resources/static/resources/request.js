function sendRequest(httpMethod, endpoint, data) {

    return new Promise(function (resolve, reject) {
        let request = new XMLHttpRequest();
        request.open(httpMethod,"http://localhost:8080/api/"+endpoint, true);
        request.setRequestHeader("Content-Type", "application/json");
        //request.responseType = 'json';
        request.onload = function () {
            if (request.status >= 200 && request.status < 300) {
                if (isJson(request.response)) {
                    let response = JSON.parse(request.response);
                    if(response.isError){
                        throwError(response.error);
                        reject(response);
                    }
                    resolve(response);
                }
                else {
                    throwError("It was not possible to interpretate the response from the server.");
                    console.error(request.response.error);
                    reject(response);
                }
            }
            else {
                throwError("It was not possible to process the request.");
                reject(response);
            }
        };
        request.onerror = function () {
            throwError("It was not possible to process the request.");
            reject(response);

        };
        request.send(JSON.stringify(data));
    });

    function isJson(str) {
        try {
            JSON.parse(str);
        }
        catch (e) {
            return false;
        }
        return true;
    }
}