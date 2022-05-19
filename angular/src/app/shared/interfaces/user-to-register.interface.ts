import { UserAddress } from "./user-address.interface";
import { UserPayment } from "./user-payment.interface";

export interface UserToRegister {
  username: string,
  password: string,
  firstName: string,
  lastName: string,
  email: string,
  userAddress?: UserAddress,
  payment?: UserPayment
}
