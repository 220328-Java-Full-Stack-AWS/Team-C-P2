export interface UserPayment {
  userId: number,
  network: string,
  issuer?: string | null,
  cardNumber: number,
  securityCode: number,
  expDate: string
}
