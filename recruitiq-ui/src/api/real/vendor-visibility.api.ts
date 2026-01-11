const API_BASE = import.meta.env.VITE_API_BASE_URL;

export const vendorVisibilityApi = {
  getByVendorId: async (vendorId: string) => {
    const res = await fetch(`${API_BASE}/api/v1/vendor-visibility/vendor/${vendorId}`, {
      headers: { Authorization: `Bearer ${sessionStorage.getItem("accessToken")}` },
    });
    if (!res.ok) throw new Error("Failed to fetch vendor visibility");
    return res.json();
  },

  create: async (data: unknown) => {
    const res = await fetch(`${API_BASE}/api/v1/vendor-visibility`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("accessToken")}`,
      },
      body: JSON.stringify(data),
    });
    if (!res.ok) throw new Error("Failed to create vendor visibility");
    return res.json();
  },
};