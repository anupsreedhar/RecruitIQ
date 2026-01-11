export type UserProfile = {
  name: string;
  email: string;
  role: string;
};

export const userService = {
  getProfile: async (): Promise<UserProfile> => {
    // In a real app, this would call an API endpoint
    // For now, we'll decode the JWT token to get user info
    const token = sessionStorage.getItem("accessToken");
    if (!token) throw new Error("No token found");

    const payload = JSON.parse(atob(token.split(".")[1]));
    return {
      name: payload.name,
      email: payload.email,
      role: payload.role,
    };
  },

  updateProfile: async (updates: Partial<Pick<UserProfile, "name" | "email">>): Promise<UserProfile> => {
    // In a real app, this would call an API endpoint to update the profile
    // For now, we'll simulate an update and return the updated profile
    const currentProfile = await userService.getProfile();
    const updatedProfile = { ...currentProfile, ...updates };

    // Update the JWT token in sessionStorage with new data
    const token = sessionStorage.getItem("accessToken");
    if (token) {
      const parts = token.split(".");
      const payload = JSON.parse(atob(parts[1]));
      const newPayload = { ...payload, ...updates };
      const newToken = [parts[0], btoa(JSON.stringify(newPayload)), parts[2]].join(".");
      sessionStorage.setItem("accessToken", newToken);
    }

    return updatedProfile;
  },
};