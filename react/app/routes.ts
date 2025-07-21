import { index, layout, prefix, route, type RouteConfig } from "@react-router/dev/routes";

export default [
  // route("/", { redirect: "/home" }),

  layout("routes/dashboard.tsx", [
    route("home", "routes/home/home.tsx"),
    route("teams", "routes/teams/teams.tsx")
  ]),

  ...prefix("auth", [
    route("signIn", "routes/auth/signIn.tsx"),
    route("signUp", "routes/auth/signUp.tsx"),
  ])
] satisfies RouteConfig;
