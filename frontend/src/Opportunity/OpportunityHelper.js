export function parseSkills(skills) {
  if (!skills) return;

  let names = [];
  skills.forEach(skill => {
    names.push(skill.name);
  });

  return names.join(', ');
}