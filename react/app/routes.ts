import { index, prefix, route, type RouteConfig } from "@react-router/dev/routes";

export default [
  index("routes/dashboard.tsx"),

  ...prefix("auth", [
    route("signIn", "routes/auth/signIn.tsx"),
    route("signUp", "routes/auth/signUp.tsx"),
  ])
] satisfies RouteConfig;
