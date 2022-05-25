import { UserProfile } from "../User-Interface/user-profile.interface";

export interface ActiveCart {
    id: number,
    user: UserProfile,
    total: number,
  }