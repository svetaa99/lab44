export const config = {
  API_URL: `http://localhost:8000`,
}
import axios from 'axios';
axios.defaults.headers.common['Authorization'] = 'Bearer ' + JSON.parse(localStorage.getItem('jwt')).accessToken; // for all requests

