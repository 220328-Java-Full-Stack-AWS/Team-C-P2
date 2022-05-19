export interface UserAddress {
  id?: number,
  addressLine1: string,
  addressLine2?: string,
  city: string,
  postalCode: number,
  country: string,
  phoneNumber?: string
}
