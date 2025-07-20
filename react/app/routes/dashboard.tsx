import { data, redirect } from "react-router";
import { me } from "~/gen/user-controller/user-controller";

export async function clientLoader() {
  const response = await me({ credentials: 'include' });

  if (response.status !== 200) {
    return redirect("/auth/signIn")
  }

  return data({});
}

export default function Dashboard() {
  return (<>Hello world</>)
}
