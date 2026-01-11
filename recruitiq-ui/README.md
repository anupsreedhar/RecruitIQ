recruitiq-ui
RecruitIQ UI

RecruitIQ UI is the frontend application for the RecruitIQ platform. It provides an intuitive user interface for recruiters, hiring managers, and candidates to interact with the system.

🧩 Technology Stack

Framework: React / Angular (choose applicable)

Language: TypeScript / JavaScript

Styling: CSS / SCSS / Tailwind / Material UI

State Management: Redux / Context API (if applicable)

Build Tool: Vite / Webpack / Angular CLI

📂 Project Structure
recruitiq-ui/
├── src/
│   ├── components/
│   ├── pages/
│   ├── services/
│   ├── hooks/
│   ├── styles/
│   └── utils/
├── public/
├── package.json
├── tsconfig.json
└── README.md
🚀 Getting Started
Prerequisites

Node.js 18+

npm / yarn / pnpm

Install & Run
cd recruitiq-ui
npm install
npm start

or

npm run dev

Application runs at:

http://localhost:3000
🔗 Backend Integration

The UI communicates with RecruitIQ Backend APIs:

Base URL: http://localhost:8080/api

Configuration is managed via environment files:

.env
.env.development
.env.production
🧪 Testing
npm test
📦 Build for Production
npm run build

Output:

dist/ or build/
🔐 Security Considerations

JWT stored securely (HttpOnly cookies recommended)

Role-based UI rendering

API error handling & validation

👨‍💻 Maintainer

Anup Sreedharan
RecruitIQ Platform

📌 Notes

This repository follows a monorepo structure:

RecruitIQ/
├── recruitiq-backend
└── recruitiq-ui

Each module can be built and deployed independently.

I’ve created proper, production-ready README templates for both modules in the canvas.

What to do next (important)

Copy the first section into
recruitiq-backend/README.md

Copy the second section into
recruitiq-ui/README.md

This keeps your monorepo clean and professional.

Why this README structure is good

✅ Clear module ownership (Backend vs UI)

✅ Architect-level details (stack, security, deployment)

✅ Easy onboarding for new developers

✅ Ready for enterprise reviews & audits

Optional improvements (I can do next)

Add API examples per controller

Add architecture diagram section
