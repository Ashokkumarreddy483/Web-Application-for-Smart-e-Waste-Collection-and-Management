import React from 'react';
import { useAuth } from '../context/AuthContext';
import bannerImg from '../assets/img.png';
import mobileImg from '../assets/mobile.png';
import laptopImg from '../assets/laptop.png';
import tvImg from '../assets/tv.png';
import batteryImg from '../assets/battery.png';
import printerImg from '../assets/printer.png';
import './HomePage.css';

const HomePage = () => {
  const { isAuthenticated, user } = useAuth();

  const services = [
    { title: 'Collection Scheduling', desc: 'Schedule pickups for your e-waste at your convenience.' },
    { title: 'Recycling Management', desc: 'We ensure your e-waste is recycled safely and efficiently.' },
    { title: 'Corporate Solutions', desc: 'Helping companies manage large volumes of electronic waste responsibly.' }
  ];

  const categories = [
    { name: 'Mobile Phones', img: mobileImg },
    { name: 'Laptops', img: laptopImg },
    { name: 'TVs & Monitors', img: tvImg },
    { name: 'Batteries', img: batteryImg },
    { name: 'Printers', img: printerImg },
  ];

  const stats = [
    { label: 'E-Waste Collected', value: '15,000+' },
    { label: 'Happy Users', value: '5,000+' },
    { label: 'Corporate Clients', value: '120+' },
    { label: 'Recycling Centers', value: '15+' },
  ];

  const testimonials = [
    { name: 'Alice', feedback: 'EcoCycle made disposing my old devices so easy!', img: mobileImg },
    { name: 'Bob', feedback: 'Professional and efficient service. Highly recommend!', img: laptopImg },
    { name: 'Charlie', feedback: 'Great awareness campaign about e-waste recycling.', img: tvImg },
  ];

  return (
    <div>
      {/* Hero Section */}
      <section className="hero-section">
        <h1>Welcome to EcoCycle E-Waste Management!</h1>
        <p>Manage, recycle, and dispose your e-waste responsibly with EcoCycle.</p>
        <img src={bannerImg} alt="E-Waste Banner" />
        {isAuthenticated ? (
          <p>Hello, <strong>{user.firstName} {user.lastName}</strong> ({user.username})! Your role: <strong>{user.role}</strong></p>
        ) : (
          <p>Please <a href="/login">Login</a> or <a href="/register">Register</a> to start managing your e-waste.</p>
        )}
      </section>

      {/* Services Section */}
      <section style={{ backgroundColor: '#e6f7ff' }}>
        <h2 style={{ textAlign: 'center' }}>Our Services</h2>
        <div className="cards-container">
          {services.map((s, i) => (
            <div className="card" key={i}>
              <h3>{s.title}</h3>
              <p>{s.desc}</p>
            </div>
          ))}
        </div>
      </section>

      {/* Categories Section */}
      <section>
        <h2 style={{ textAlign: 'center' }}>E-Waste Categories</h2>
        <div className="cards-container">
          {categories.map((c, i) => (
            <div className="card" key={i}>
              <img src={c.img} alt={c.name} style={{ width: '150px', borderRadius: '10px', marginBottom: '10px' }} />
              <h4>{c.name}</h4>
              <p>Dispose of your {c.name.toLowerCase()} responsibly and sustainably.</p>
            </div>
          ))}
        </div>
      </section>

      {/* Dashboard */}
      {isAuthenticated && (
        <section style={{ backgroundColor: '#f9f9f9', textAlign: 'center' }}>
          <h2>Your Dashboard</h2>
          <p>View your e-waste requests, status updates, and collection schedules.</p>
          <a href="/profile" style={{ padding: '10px 20px', backgroundColor: '#007acc', color: '#fff', borderRadius: '5px' }}>Go to Profile</a>
        </section>
      )}

      {/* Stats */}
      <section style={{ backgroundColor: '#e6f7ff' }}>
        <h2 style={{ textAlign: 'center' }}>Our Impact</h2>
        <div className="stats-container">
          {stats.map((s, i) => (
            <div key={i}>
              <h3>{s.value}</h3>
              <p>{s.label}</p>
            </div>
          ))}
        </div>
      </section>

      {/* Testimonials */}
      <section>
        <h2 style={{ textAlign: 'center' }}>What Our Users Say</h2>
        <div className="cards-container">
          {testimonials.map((t, i) => (
            <div className="card" key={i}>
              <img src={t.img} alt={t.name} style={{ width: '100px', borderRadius: '50%', marginBottom: '10px' }} />
              <p>"{t.feedback}"</p>
              <p><strong>- {t.name}</strong></p>
            </div>
          ))}
        </div>
      </section>

      {/* FAQ */}
      <section style={{ backgroundColor: '#f9f9f9' }}>
        <h2 style={{ textAlign: 'center' }}>Frequently Asked Questions</h2>
        <div style={{ maxWidth: '800px', margin: '0 auto' }}>
          <details><summary>How do I schedule a pickup?</summary><p>Login to your account and submit a pickup request with details of your e-waste.</p></details>
          <details><summary>What items can I recycle?</summary><p>We accept electronics like phones, laptops, TVs, batteries, and other e-waste items.</p></details>
          <details><summary>Is there a cost for collection?</summary><p>For individual users, collection is usually free. Corporate clients may have customized plans.</p></details>
        </div>
      </section>

      {/* Contact */}
      <section>
        <h2 style={{ textAlign: 'center' }}>Contact Us</h2>
        <form className="contact-form">
          <input type="text" placeholder="Name" required />
          <input type="email" placeholder="Email" required />
          <textarea placeholder="Message" required />
          <button type="submit">Send Message</button>
        </form>
      </section>

      {/* Footer */}
      <footer>
        <p>© 2025 EcoCycle E-Waste Management. All rights reserved.</p>
        <p><a href="/privacy">Privacy Policy</a><a href="/terms">Terms & Conditions</a></p>
      </footer>
    </div>
  );
};

export default HomePage;
