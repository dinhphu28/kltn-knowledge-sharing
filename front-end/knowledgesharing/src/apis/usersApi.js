import axiosClient from "./axiosClient";

const usersApi = {

    getAll: (params) => {
        const url = '/users';

        return axiosClient.get(url, {params});
    },

    // create mod
    post: (data) => {
        const url = '/users';

        return axiosClient.post(url, data);
    },

    putActiveState: (data) => {
        const url = '/users/active-state';

        return axiosClient.put(url, data);
    },

    putPassword: (data) => {
        const url = '/users/password';

        return axiosClient.put(url, data);
    }
};

export default usersApi;