import axios from 'axios';

let axiosInstance = axios.create({
    baseURL: 'http://localhost:8082/',
    timeout: 120000
});

axiosInstance.interceptors.request.use(function (config) {
    // Do something before request is sent
    return config
}, function (error) {
    // Do something with request error
    console.log('erorieeeeee2');
    return Promise.reject(error)
});

// Add a response interceptor
axiosInstance.interceptors.response.use(function (response) {
    // Do something with response data
    return response.data
}, function (err) {
    return new Promise(function (resolve, reject) {
        console.log('erorieeeeee');
        throw err;
    });
    // return Promise.reject(error.response);
});

class HttpRequest {
    constructor() {
        this.axios = axios;
    }

    setHeader(header) {
        axiosInstance.defaults.headers.common[header.key] = header.value;
        axiosInstance.defaults.headers.post['Accept'] = 'application/json';
        axiosInstance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
    }

    fetch(methodName, data = {}) {
        return axiosInstance.get(methodName, {params: data})
    }

    create(methodName, data) {
        return axiosInstance.post(methodName, data)
    }

    update(methodName, data) {
        return axiosInstance.put(methodName, data)
    }

    delete(methodName, data) {
        return axiosInstance.delete(methodName, data)
    }

    request(type, url, data) {
        // this.addToken(data);
        let promise = null;

        // console.log('dataaa', data);
        switch (type) {
            case 'GET':
                promise = axios.get(url, {params: data});
                break;
            case 'POST':
                promise = axios.post(url, data);
                break;
            case 'PUT':
                promise = axios.put(url, data);
                break;
            case 'DELETE':
                promise = axios.delete(url, data);
                break;
            default :
                promise = axios.get(url, {params: data});
                break;
        }
        return promise
    }
}

export default HttpRequest