export interface UserPayment {
  id?: number,
  network: string,
  issuer?: string,
  cardNumber: number,
  securityCode: number,
  expirationDate: string
}
