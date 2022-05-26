import { UserProfile } from "../User-Interface/user-profile.interface";

export interface CartOrder {
    id: number,
    user: UserProfile,
    total: number
  }