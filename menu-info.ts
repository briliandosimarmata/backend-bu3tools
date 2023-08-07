export const MENU_INFO_MASTER: MenuInfo = {
  menuId: '0000',
  menuSequence: '01',
  icon: 'fa fa-cogs',
};

export const MENU_INFO_LIST: MenuInfo[] = [
  MENU_INFO_MASTER,
  {
    menuId: '0000',
    menuSequence: '23',
    icon: 'fa fa-university',
  },
  {
    menuId: '000023',
    menuSequence: '00',
  },
  {
    menuId: '000023',
    menuSequence: '01',
  },
  {
    menuId: '00002301',
    menuSequence: '00',
  },
  {
    menuId: '00002301',
    menuSequence: '02',
    url: 'top-claim-principal',
  },
  {
    menuId: '00002301',
    menuSequence: '03',
    url: 'prm-bailout-ceiling',
  },
  {
    menuId: '00002301',
    menuSequence: '04',
    url: 'user-vs-dashboard-item',
  },
  {
    menuId: '00002301',
    menuSequence: '05',
    url: 'user-vs-user-type',
  },
  {
    menuId: '00002301',
    menuSequence: '06',
    url: 'pph-percent-role',
  },
  {
    menuId: '000023',
    menuSequence: '02',
  },
  {
    menuId: '00002302',
    menuSequence: '00',
  },
  {
    menuId: '00002302',
    menuSequence: '01',
    url: 'prgm-submission',
  },
  {
    menuId: '00002302',
    menuSequence: '02',
    url: 'prgm-submission-asm',
  },
  {
    menuId: '00002302',
    menuSequence: '03',
    url: 'prgm-submission-ho',
  },
  {
    menuId: '00002302',
    menuSequence: '04',
    url: 'claim-submission',
  },
  {
    menuId: '00002302',
    menuSequence: '05',
    url: 'claim-submission-agreement-asm',
  },
  {
    menuId: '00002302',
    menuSequence: '06',
    url: 'claim-submission-agreement-ho',
  },
  {
    menuId: '00002302',
    menuSequence: '07',
    url: 'claim-payment',
  },
  {
    menuId: '00002302',
    menuSequence: '08',
    url: 'update-reference-claim-submission',
  },
  {
    menuId: '00002302',
    menuSequence: '09',
    url: 'ho-cvr-letter',
  },
  {
    menuId: '000023',
    menuSequence: '03',
  },
  {
    menuId: '00002303',
    menuSequence: '00',
  },
  {
    menuId: '00002303',
    menuSequence: '01',
    url: 'inquiry-prgm-submission',
  },
  {
    menuId: '00002303',
    menuSequence: '02',
    url: 'inquiry-claim',
  },
  {
    menuId: '00002303',
    menuSequence: '03',
    url: 'inquiry-prgm-aggrement',
  },
  {
    menuId: '0000',
    menuSequence: '24',
    icon: 'fas fa-money-bill-wave',
  },
  {
    menuId: '000024',
    menuSequence: '00',
  },
  {
    menuId: '000024',
    menuSequence: '01',
  },
  {
    menuId: '00002401',
    menuSequence: '00',
  },
  {
    menuId: '00002401',
    menuSequence: '01',
    url: 'base-selling-price',
  },
  {
    menuId: '00002401',
    menuSequence: '02',
    url: 'cust-top-payment-duration',
  },
  {
    menuId: '00002401',
    menuSequence: '03',
    url: 'pct-index-top-calculation',
  },
  {
    menuId: '00002401',
    menuSequence: '04',
    url: 'ka-contribution-percent',
  },
  {
    menuId: '00002401',
    menuSequence: '05',
    url: 'top-deviation-limit',
  },
  {
    menuId: '00002401',
    menuSequence: '06',
    url: 'weeks-cash-flow',
  },
  {
    menuId: '000024',
    menuSequence: '03',
  },
  {
    menuId: '00002403',
    menuSequence: '00',
  },
  {
    menuId: '00002403',
    menuSequence: '01',
    url: 'process-cash-flow',
  },
  {
    menuId: '000024',
    menuSequence: '04',
  },
  {
    menuId: '00002404',
    menuSequence: '01',
    url: 'cash-flow-national-reports',
  },
  {
    menuId: '00002404',
    menuSequence: '02',
    url: 'branch-reports',
  },
  {
    menuId: '00002404',
    menuSequence: '03',
    url: 'cash-position-report',
  },
  {
    menuId: '0000',
    menuSequence: '99',
  },
  {
    menuId: '000099',
    menuSequence: '00',
  },
  {
    menuId: '000099',
    menuSequence: '01',
  },
  {
    menuId: '00009901',
    menuSequence: '00',
  },
  {
    menuId: '00009901',
    menuSequence: '01',
  },
  {
    menuId: '000099',
    menuSequence: '02',
  },
];

interface MenuInfo {
  menuId: string;
  menuSequence: string;
  icon?: string;
  url?: string;
}