import {LOCAL_NAME_TOKEN} from "../utils/constants.ts";


const setToken = (token: string) => {
  localStorage.setItem(LOCAL_NAME_TOKEN, token)
}

const getToken = () => {
  return localStorage.getItem(LOCAL_NAME_TOKEN)
}

const removeToken = () => {
  localStorage.removeItem(LOCAL_NAME_TOKEN)
}

export default {setToken, getToken, removeToken}
