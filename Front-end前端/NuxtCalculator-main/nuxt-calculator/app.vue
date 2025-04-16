<template>
  <div>
    <NuxtPage />
  </div>
</template>

<style>
@import '@fortawesome/fontawesome-free/css/all.css';

:root {
  --primary-bg: #f0f2f5;
  --dark-bg: #1a1a1a;
  --calculator-bg: rgba(255, 255, 255, 0.95);
  --calculator-dark-bg: #2d2d2d;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  height: 100%;
}

body {
  font-family: 'Poppins', 'Segoe UI', sans-serif;
  background-color: var(--primary-bg);
  transition: background-color 0.3s ease;
}

body.dark-mode {
  background-color: var(--dark-bg);
  color: #e0e0e0;
}

.calculator {
  background-color: var(--calculator-bg);
  padding: 1.5rem;
  border-radius: 20px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
  width: 320px;
  transition: all 0.3s ease;
}

body.dark-mode .calculator {
  background-color: var(--calculator-dark-bg);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.3);
}

#display {
  width: 100%;
  height: 70px;
  font-size: 1.75rem;
  font-family: 'Poppins', monospace;
  text-align: right;
  padding: 0.5rem 1rem;
  margin-bottom: 1.2rem;
  border: none;
  border-radius: 12px;
  background-color: #f8f9fa;
  color: #212529;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
}

body.dark-mode #display {
  background-color: #383838;
  color: #e0e0e0;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.2);
}

.buttons {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 12px;
  width: 100%;
}

button {
  height: 65px;
  font-family: 'Poppins', sans-serif;
  font-size: 1.2rem;
  border: none;
  border-radius: 12px;
  background-color: #fff;
  color: #424242;
  cursor: pointer;
  transition: all 0.2s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
}

body.dark-mode button {
  background-color: #383838;
  color: #e0e0e0;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}

button:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

button:active {
  transform: translateY(0);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.number {
  background-color: #ffffff;
}

body.dark-mode .number {
  background-color: #404040;
}

.operator {
  background-color: #f8f9fa;
  color: #0ca678;
  font-weight: 500;
}

body.dark-mode .operator {
  background-color: #333;
  color: #12b886;
}

.function-btn {
  background-color: #e9ecef;
  color: #495057;
}

body.dark-mode .function-btn {
  background-color: #2d2d2d;
  color: #adb5bd;
}

.equal {
  background-color: #0ca678;
  color: white;
}

body.dark-mode .equal {
  background-color: #12b886;
}

.theme-toggle {
  position: fixed;
  top: 20px;
  right: 20px;
  width: 50px;
  height: 50px;
  border-radius: 25px;
  background: #fff;
  border: none;
  cursor: pointer;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

body.dark-mode .theme-toggle {
  background: #383838;
}

.sun-icon, .moon-icon {
  font-size: 1.3rem;
  position: absolute;
  transition: all 0.3s ease;
}

.sun-icon {
  color: #ffd43b;
  opacity: 1;
  transform: rotate(0deg);
}

.moon-icon {
  color: #74c0fc;
  opacity: 0;
  transform: rotate(90deg);
}

.theme-toggle.dark-mode .sun-icon {
  opacity: 0;
  transform: rotate(-90deg);
}

.theme-toggle.dark-mode .moon-icon {
  opacity: 1;
  transform: rotate(0deg);
}

@media (max-width: 480px) {
  .calculator {
    width: calc(100% - 2rem);
    margin: 1rem;
    padding: 1rem;
  }

  button {
    height: 55px;
    font-size: 1rem;
  }

  #display {
    font-size: 1.5rem;
    height: 60px;
  }
}
</style>
