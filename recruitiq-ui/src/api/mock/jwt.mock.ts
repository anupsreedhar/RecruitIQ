// src/api/mock/jwt.mock.ts
type JwtPayload = {
  role: string;
  name: string;
  email: string;
  exp: number;
};

const base64UrlEncode = (obj: object) => {
  return btoa(JSON.stringify(obj))
    .replace(/=/g, "")
    .replace(/\+/g, "-")
    .replace(/\//g, "_");
};

export const createMockJwt = (payload: JwtPayload): string => {
  const header = {
    alg: "HS256",
    typ: "JWT",
  };

  return [
    base64UrlEncode(header),
    base64UrlEncode(payload),
    "mock-signature",
  ].join(".");
};
