const ID_TOKEN_KEY = "Authorization";

export const getToken = () => {
  console.log("JWT acquired");
  return window.sessionStorage.getItem(ID_TOKEN_KEY);
};

export const saveToken = (token) => {
  console.log("JWT maden : " + token);
  window.sessionStorage.setItem(ID_TOKEN_KEY, token);
};

export const destroyToken = () => {
  console.log("JWT destroyed");
  window.sessionStorage.removeItem(ID_TOKEN_KEY);
};

export default {
  getToken,
  saveToken,
  destroyToken,
};
