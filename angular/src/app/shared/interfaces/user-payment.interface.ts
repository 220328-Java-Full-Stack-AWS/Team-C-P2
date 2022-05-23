export interface UserPayment {
  userId: number,
  network: string,
  issuer?: string | null,
  cardNumber: string,
  securityCode: number,
  expDate: string
}
