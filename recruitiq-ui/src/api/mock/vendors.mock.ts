// src/api/mock/vendors.mock.ts

export type MockVendor = {
  id: string;
  name: string;
  contactEmail: string;
  active: boolean;
};

const vendors: MockVendor[] = [
  {
    id: "v1",
    name: "ABC Consulting",
    contactEmail: "contact@abc.com",
    active: true,
  },
  {
    id: "v2",
    name: "TechHire Solutions",
    contactEmail: "hr@techhire.com",
    active: true,
  },
];

export const mockVendorsApi = {
  list: async (): Promise<MockVendor[]> => {
    await delay(400);
    return vendors;
  },

  create: async (vendor: Partial<MockVendor>): Promise<MockVendor> => {
    await delay(500);
    const newVendor: MockVendor = {
      id: `v${Date.now()}`,
      name: vendor.name || "New Vendor",
      contactEmail: vendor.contactEmail || "",
      active: true,
    };
    vendors.push(newVendor);
    return newVendor;
  },
};

const delay = (ms: number) =>
  new Promise((resolve) => setTimeout(resolve, ms));
