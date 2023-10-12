import { SecurityValues } from './security-values.model';

export class DTUser {
  fullName: string;
  email: string;
  phoneNumber: string;
  dateofbirth: Date;
  userName: string;
  securityValues: SecurityValues[];
  password: string;
}
