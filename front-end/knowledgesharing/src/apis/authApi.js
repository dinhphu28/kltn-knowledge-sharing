import axiosClient from "./axiosClient";

const authApi = {
    // Sign In
    put: (data) => {
        const url = '/auth';

        return axiosClient.put(url, data);
    },

    // Sign Up
    post: (data) => {
        const url = '/auth';

        return axiosClient.post(url, data);
    },

    // Update password
    patch: (username, data) => {
        const url = `/auth/${username}`;

        return axiosClient.patch(url, data);
    },

    // Send request: forgot password and send email to user
    getTokenResetPasswd: (username) => {
        const url = `/auth/forget-password/${username}`;

        return axiosClient.get(url);
    },

    // Exec reset password from username and token (token has been sent in email)
    resetPasswd: (username, data) => {
        const url = `/auth/forget-password/${username}`;

        return axiosClient.patch(url, data);
    }
}

export default authApi;