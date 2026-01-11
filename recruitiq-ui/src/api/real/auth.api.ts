const API_BASE = import.meta.env.VITE_API_BASE_URL;

export const authApi = {
  login: async (email: string, password: string) => {
    const res = await fetch(`${API_BASE}/auth/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });

    if (!res.ok) {
      throw new Error("Authentication failed");
    }

    return res.json();
  },
};
