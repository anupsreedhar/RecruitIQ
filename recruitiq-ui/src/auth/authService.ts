import { apiClient } from "../api/apiClient";

export const authService = {
  login: async (email: string, password: string) => {
    console.log("[AUTH_SERVICE] login called with email:", email, "password:", password.replace(/./g, "*"));
    const result = await apiClient.auth.login(email, password);
    console.log("[AUTH_SERVICE] login success, token length:", result.token.length);
    return result;
  },

  logout: () => {
    sessionStorage.clear();
    window.location.href = "/login";
  },
};
