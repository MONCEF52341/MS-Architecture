/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    remotePatterns:

    [
      {
        protocol: 'https',
        hostname: 'picsum.photos',
        port: '',
        pathname: '/id/**/300/100',
        search: '',
      },
    ],
  },
}



export default nextConfig;
