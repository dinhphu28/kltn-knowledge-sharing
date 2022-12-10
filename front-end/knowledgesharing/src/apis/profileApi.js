import axiosClient from "./axiosClient";

const profileApi = {
    get: (username) => {
        const url = `/profiles/${username}`;

        return axiosClient.get(url);
    },

    put: (username, data) => {
        const url = `/profiles/${username}`;
        
        // return axiosClient.put(url, data, {
        //     headers: {
        //         'Authorization': 'Bearer ' + localStorage.getItem("token")
        //     }
        // });

        return axiosClient.put(url, data);
    }
}

export default profileApi;