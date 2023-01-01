import axiosClient from "./axiosClient";

const userNotificationApi = {
    getAll: (params) => {
        const url = "/user-notification";

        return axiosClient.get(url, {params});
    },
    
    post: (data) => {
        const url = "/user-notification";

        return axiosClient.post(url, data);
    }
};

export default userNotificationApi;