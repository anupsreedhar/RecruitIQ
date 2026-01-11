import { createMockJwt } from "./jwt.mock";

export type MockAuthResponse = {
  token: string;
};

export const authMockApi = {
  login: async (email: string, password: string): Promise<MockAuthResponse> => {
    console.log("[AUTH_MOCK] login called with email:", email, "password:", password.replace(/./g, "*"));
    await delay(400);

    const trimmedEmail = email.trim();
    const trimmedPassword = password.trim();
    console.log("[AUTH_MOCK] trimmed email:", trimmedEmail, "trimmed password length:", trimmedPassword.length);

    const exp = Math.floor(Date.now() / 1000) + 60 * 60;

    if (trimmedEmail === "admin@recruitiq.com" && trimmedPassword === "admin123") {
      console.log("[AUTH_MOCK] admin credentials matched");
      return {
        token: createMockJwt({
          role: "AGENCY_ADMIN",
          name: "RecruitIQ Admin",
          email,
          exp,
        }),
      };
    }

    if (trimmedEmail === "recruiter@recruitiq.com" && trimmedPassword === "recruiter123") {
      console.log("[AUTH_MOCK] recruiter credentials matched");
      return {
        token: createMockJwt({
          role: "RECRUITER",
          name: "Recruiter User",
          email,
          exp,
        }),
      };
    }

    if (trimmedEmail === "vendor@company.com" && trimmedPassword === "vendor123") {
      console.log("[AUTH_MOCK] vendor credentials matched");
      return {
        token: createMockJwt({
          role: "VENDOR_USER",
          name: "Vendor User",
          email,
          exp,
        }),
      };
    }

    console.log("[AUTH_MOCK] no credentials matched, throwing error");
    throw new Error("Invalid credentials");
  },
};

const delay = (ms: number) =>
  new Promise((resolve) => setTimeout(resolve, ms));
