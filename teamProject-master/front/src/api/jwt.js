const ID_TOKEN_KEY = "Authorization";

export const getToken = () => {
  const token = window.sessionStorage.getItem(ID_TOKEN_KEY);
  console.log("JWT load : " + token);
  return token;
};

export const saveToken = (token) => {
  console.log("JWT save : " + token);
  window.sessionStorage.setItem(ID_TOKEN_KEY, token);
};

export const destroyToken = () => {
  const token = window.sessionStorage.removeItem(ID_TOKEN_KEY);
  console.log("JWT destroyed : " + token);
};

export default {
  getToken,
  saveToken,
  destroyToken,
};
