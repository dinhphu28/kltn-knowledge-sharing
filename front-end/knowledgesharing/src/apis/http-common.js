import axios from "axios";
import { BASE_URL_API_BE } from "../constants/global";

export default axios.create({
    // baseURL: "http://localhost:8080/api/v1",
    baseURL: BASE_URL_API_BE,
    headers: {
        "Content-type": "application/json"
    }
});