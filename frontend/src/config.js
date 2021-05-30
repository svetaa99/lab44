export const config = {
  API_URL: `http://localhost:8000`,
}
import axios from 'axios';
axios.defaults.headers.common['Authorization'] = 'Bearer ' + getWithExpiry(); // for all requests

function getWithExpiry() {
	const itemStr = localStorage.getItem('jwt')
	// if the item doesn't exist, return null
	if (!itemStr) {
		return null
	}
	const item = JSON.parse(itemStr)
	const now = new Date()
	// compare the expiry time of the item with the current time
	if (now.getTime() > item.expiry) {
		// If the item is expired, delete the item from storage
		// and return null
		localStorage.removeItem('jwt');
		return null
	}
	return item.token.accessToken
}