
function openEmailModal(email) {
  document.getElementById('emailModalRecipient').value = '';
  document.getElementById('emailModalIntent').value = '';
  document.getElementById('emailModalMessage').value = '';
  document.getElementById('emailModal').classList.remove('hidden');
}

function closeEmailModal() {
  document.getElementById('emailModal').classList.add('hidden');
}

function generateEmailWithAI() {
  const intent = document.getElementById('emailModalIntent').value;
  const messageBox = document.getElementById('emailModalMessage');
  if (!intent) {
    messageBox.value = "Please enter an intent first.";
    return;
  }
  // Simulate AI generation (replace with real API if needed)
  messageBox.value = `Hi,\n\nI would like to ${intent}.\n\nBest regards,\n[Your Name]`;
}

function sendEmailFromModal() {
  const recipient = document.getElementById('emailModalRecipient').value;
  const intent = document.getElementById('emailModalIntent').value;
  const message = document.getElementById('emailModalMessage').value;
  // TODO: Implement your send email logic here (AJAX or form submit)
  alert(`Email sent to ${recipient}!\n\nIntent: ${intent}\n\nMessage:\n${message}`);
  closeEmailModal();
}