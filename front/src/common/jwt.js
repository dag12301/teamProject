const ID_TOKEN_KEY = "Authorization";

export const getToken = () => {
  console.log("키 획득");
  return window.localStorage.getItem(ID_TOKEN_KEY);
};

export const saveToken = (token) => {
  console.log("획득한 토큰 : " + token);
  window.localStorage.setItem(ID_TOKEN_KEY, token);
};

export const destroyToken = () => {
  console.log("키 파괴");
  window.localStorage.removeItem(ID_TOKEN_KEY);
};

export default {
  getToken,
  saveToken,
  destroyToken,
};
