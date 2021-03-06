#!/usr/bin/env ruby


require 'nakamura/test'
require 'nakamura/search'
require 'test/unit.rb'
include SlingSearch

class TC_Kern325Test < Test::Unit::TestCase
  include SlingTest


  def test_site_template_versioning
    m = uniqueness()
    template_path = "/dummy_template#{m}"
    template_site = @sm.create_site("templates", "A Template", "#{template_path}")
    @s.execute_post(@s.url_for(template_path), "sakai:is-site-template" => "true")
    template = create_node("#{template_site.path}/a1#{m}", "fish" => "cat")
    site = @sm.create_site("sites", "Site test", "/testsite#{m}", "#{template_path}")
    versions = @s.versions(site.path)
    assert(versions.size > 1, "Expected node '#{site.path}' to get some versions back")
  end

end
