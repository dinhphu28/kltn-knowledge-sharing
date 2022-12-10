import axiosClient from "./axiosClient";

const emailVerificationApi = {
    post: (data) => {
        const url = '/email-verification';

        return axiosClient.post(url, data);
    }
};

export default emailVerificationApi;