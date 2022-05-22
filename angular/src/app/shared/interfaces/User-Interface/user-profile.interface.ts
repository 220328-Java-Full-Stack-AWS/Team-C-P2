import { UserAddress } from "../user-address.interface";
import { UserPayment } from "../user-payment.interface";

export interface UserProfile {
    id: number,
    username: string,
    firstName: string,
    lastName: string,
    email: string,
    role: string,
    activeCartId: number,
    userAddress: UserAddress,
    payment: UserPayment,
    dateCreated: string,
    dateModifies: string
}
